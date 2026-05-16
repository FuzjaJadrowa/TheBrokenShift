package pl.fuzjajadrowa.thebrokenshift.mixin;

import net.minecraft.client.KeyboardHandler;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {
    @Inject(method = "keyPress", at = @At("HEAD"), cancellable = true)
    private void thebrokenshift$onKeyPress(long window, int key, int scancode, int action, int mods, CallbackInfo ci) {
        if (key == GLFW.GLFW_KEY_LEFT_SHIFT || key == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            ci.cancel();
        }
    }

    @ModifyVariable(method = "keyPress", at = @At("HEAD"), ordinal = 3, argsOnly = true)
    private int thebrokenshift$stripShiftMod(int mods) {
        return mods & ~GLFW.GLFW_MOD_SHIFT;
    }
}