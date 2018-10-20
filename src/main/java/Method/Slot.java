package Method;

import net.dv8tion.jda.core.entities.User;

import java.io.File;
import java.io.FileWriter;

import org.yaml.snakeyaml.*;

public class Slot {
	final protected String filePath = "../../../../data/savedata.yml";

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
	}
}
