package pl.fuzjajadrowa.thebrokenshift.mixin;

import net.minecraft.client.MouseHandler;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @ModifyVariable(method = "onPress", at = @At("HEAD"), ordinal = 2, argsOnly = true)
    private int thebrokenshift$stripShiftMod(int mods) {
        return mods & ~GLFW.GLFW_MOD_SHIFT;
    }
}