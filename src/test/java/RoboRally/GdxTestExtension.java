package RoboRally;

import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.mockito.Mockito.mock;

public class GdxTestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new RoboRallyApp(), conf);
        Gdx.gl = mock(GL20.class);
    }
}
