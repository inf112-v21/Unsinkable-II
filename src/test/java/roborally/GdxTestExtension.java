package roborally;

import roborally.gui.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.mockito.Mockito.mock;

/**
 * This class is used with other test classes with the `ExtendsWith` annotation.
 * It starts the game in headless mode, so that libgdx is initialized, which is
 * required for some tests.
 */
public class GdxTestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new RoboRallyApp(), conf);
        Gdx.gl = mock(GL20.class);
    }
}
