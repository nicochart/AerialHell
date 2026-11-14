package fr.factionbedrock.aerialhell.Client.Texture;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.atlas.SpriteResourceLoader;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.resources.metadata.animation.AnimationFrame;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.ExtraCodecs;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//on load, each copy reads the same texture (and .mcmeta) file with an offset
//one copy per phase, each copy is delayed from previous one.
//copy k is <texture>_phase_k (except copy 0 is <texture>)
//copy delay : k * animation duration / phases number
public record PhasedAnimationSpriteSource(Identifier resource, int phases) implements SpriteSource
{
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final MapCodec<PhasedAnimationSpriteSource> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Identifier.CODEC.fieldOf("resource").forGetter(PhasedAnimationSpriteSource::resource),
            ExtraCodecs.POSITIVE_INT.fieldOf("phases").forGetter(PhasedAnimationSpriteSource::phases)
        ).apply(instance, PhasedAnimationSpriteSource::new));

    public static Identifier phaseSpriteId(Identifier resource, int phase) {return phase == 0 ? resource : resource.withSuffix("_phase_" + phase);}

    @Override public void run(ResourceManager resourceManager, SpriteSource.Output output)
    {
        Optional<Resource> resource = resourceManager.getResource(TEXTURE_ID_CONVERTER.idToFile(this.resource));
        if (resource.isEmpty()) {LOGGER.warn("Missing phased animation texture: {}", this.resource); return;}
        for (int phase = 0; phase < this.phases; phase++)
        {
            Identifier spriteId = phaseSpriteId(this.resource, phase);
            int copyPhase = phase;
            output.add(spriteId, loader -> this.loadDelayedCopy(loader, spriteId, resource.get(), copyPhase));
        }
    }

    @Override public MapCodec<PhasedAnimationSpriteSource> codec() {return MAP_CODEC;}

    private @Nullable SpriteContents loadDelayedCopy(SpriteResourceLoader loader, Identifier spriteId, Resource resource, int phase)
    {
        return loader.loadSprite(spriteId, resource, (id, frameSize, image, animation, additionalMetadata, textureMetadata) ->
            new SpriteContents(id, frameSize, image, animation.map(section -> this.delayed(section, phase)), additionalMetadata, textureMetadata));
    }

    //for phase k, returns the same animation as original, but delayed by k * animation duration / number of phases (rounded to a frame change if needed)
    private AnimationMetadataSection delayed(AnimationMetadataSection animation, int phase)
    {
        List<AnimationFrame> frames = animation.frames().orElse(List.of());
        int count = frames.size();
        if (phase == 0 || count < 2) {return animation;}

        int[] index = new int[count];
        int[] time = new int[count];
        int duration = 0;
        for (int i = 0; i < count; i++)
        {
            index[i] = frames.get(i).index();
            time[i] = frames.get(i).timeOr(animation.defaultFrameTime());
            duration += time[i];
        }

        //find where the copy starts in the original list
        int startFrame = 0;
        int ticksIntoStartFrame = (duration - phase * duration / this.phases) % duration;
        while (ticksIntoStartFrame >= time[startFrame])
        {
            ticksIntoStartFrame -= time[startFrame];
            startFrame++;
        }
        boolean startsInRest = index[startFrame] == index[(startFrame + 1) % count];
        if (ticksIntoStartFrame > 0 && !startsInRest) //can't cut a moving frame without changing the interpolation : start at the nearest frame change instead
        {
            if (ticksIntoStartFrame * 2 >= time[startFrame]) {startFrame = (startFrame + 1) % count;}
            ticksIntoStartFrame = 0;
        }

        List<AnimationFrame> delayedFrames = new ArrayList<>();
        delayedFrames.add(frame(index[startFrame], time[startFrame] - ticksIntoStartFrame));
        for (int i = 1; i < count; i++)
        {
            int frame = (startFrame + i) % count;
            delayedFrames.add(frame(index[frame], time[frame]));
        }
        if (ticksIntoStartFrame > 0) {delayedFrames.add(frame(index[startFrame], ticksIntoStartFrame));} //the beginning of the rest that was cut
        return new AnimationMetadataSection(Optional.of(delayedFrames), animation.frameWidth(), animation.frameHeight(), animation.defaultFrameTime(), animation.interpolatedFrames());
    }

    private static AnimationFrame frame(int index, int time) {return new AnimationFrame(index, Optional.of(time));}
}
