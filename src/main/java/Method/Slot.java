package Method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;

import Basic.Ref;
import Random.MT;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class Slot {
	/**
	 * セーブデータ
	 * */
	final protected String filePath = System.getProperty("user.dir") + "/data/savedata.yml";

	/**
	 * メルセンヌツイスター用シード値
	 * */
	final private long seed = 20181021;

	/**
	 *
	 * */
	final private int SLOT_MAX = 10;

	/**
	 * メルセンヌツイスタークラス
	 * */
	private MT mt;

	/**
	 * データを格納するHash
	 * */
	private HashMap<String, Integer> writeData;

	/**
	 * デフォルトコンストラクタ
	 * */
	public Slot() {
		mt = new MT(seed);
		writeData = new HashMap<>();
		// データロード部
		load();
	}

	/**
	 * プレフィックス設定メソッド
	 * */
	public void doMethod(Message objMsg, MessageChannel objMsgCh, User objUser) {
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "coin")) {
			objMsgCh.sendMessage("きみは" + writeData.get(objUser.getId()) + "枚のコインを持っているよ！").queue();
		}
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "slot")){
			int x = mt.Next(1 , SLOT_MAX);
			objMsgCh.sendMessage("コインが" + x + "枚増えたよ！").queue();

			// 元からデータがある場合
			if(writeData.get(objUser.getId()) != null) {
				int tmp = writeData.get(objUser.getId());
				tmp += x;
				writeData.put(objUser.getId(), tmp);
			}
			// 初めてスロットを回す場合
			else {
				writeData.put(objUser.getId(), x);
			}

		}
	}
	/**
	 * loadメソッド
	 * */
	public void load() {
		Yaml yaml = new Yaml();
		File file = new File(filePath);
		if (file.exists()) {
			try {
				InputStream is = Files.newInputStream(Paths.get(filePath));
				writeData = yaml.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * saveメソッド
	 * */
	public void save() throws Exception {
		Yaml yaml = new Yaml();
		File file = new File(filePath);
		if (file.exists()) {
			yaml.load(filePath);
			FileWriter fileWriter = new FileWriter(filePath);

			yaml.dump(writeData, fileWriter);
		}
		else {
			throw new Exception();
		}
	}
}
