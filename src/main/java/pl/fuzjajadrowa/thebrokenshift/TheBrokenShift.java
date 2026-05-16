package pl.fuzjajadrowa.thebrokenshift;

import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(TheBrokenShift.MODID)
public class TheBrokenShift {
    public static final String MODID = "thebrokenshift";
    private static final Logger LOGGER = LoggerFactory.getLogger(TheBrokenShift.class);

    public TheBrokenShift() {
        LOGGER.info("BUAHAHAHA! Shift is now broken!");
    }
}