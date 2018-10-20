package Method;

import java.io.File;
import java.io.FileWriter;

import org.yaml.snakeyaml.Yaml;

import net.dv8tion.jda.core.entities.User;

public class Slot {
	final protected String filePath = System.getProperty("user.dir") + "/data/savedata.yml";

	public void doMethod() {

	}

	public void save(User user, int data) throws Exception {
		Yaml yaml = new Yaml();
		File file = new File(filePath);
		if (file.exists()) {
			yaml.load(filePath);
			FileWriter fileWriter = new FileWriter(filePath);
			yaml.dump("test", fileWriter);
		}
		else {
			throw new Exception();
		}
	}
}
