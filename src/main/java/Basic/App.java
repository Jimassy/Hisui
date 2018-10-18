package Basic;

import java.awt.Color;
import java.util.HashMap;

import javax.security.auth.login.LoginException;
import javax.sound.sampled.AudioFormat;			//Audio Formatのインポート文。

import Method.Gacha;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.requests.Route;

public class App extends ListenerAdapter {
	public MessageChannel objMsgCh = null;

	public static void main(String[] args) throws Exception {

		JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT)
				.setToken(Ref.token)
				.setGame(Game.playing("development jounrey"))
				.addEventListener(new App())
				.setAutoReconnect(true);

		try {
			jdaBuilder.build();
		}catch(LoginException error){
			error.printStackTrace();
		}

		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	public void onMessageReceived(MessageReceivedEvent evt) {

		//Objects
		User objUser = evt.getAuthor();
		Message objMsg = evt.getMessage();
		objMsgCh = evt.getChannel();
		Member objMem = evt.getMember();
		net.dv8tion.jda.core.entities.Guild objGld = evt.getGuild();
		User user = objMsg.getAuthor();


		//Commands
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "自己紹介"))
			objMsgCh.sendMessage(objUser.getAsMention() + "ハロー！僕はHisuiです。").queue();
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"おはよう"))
			objMsgCh.sendMessage("おはよう" +objMem.getEffectiveName() + "さん").queue();
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"hello"))
			objMsgCh.sendMessage("こんにちは、" + objUser.getAsMention() + "さん").queue();
		if(objMsg.getContentRaw().equalsIgnoreCase("おやすみ、Hisui"))
			objMsgCh.sendMessage("よく寝てくださいね！").queue();
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "avatar"))
			objMsgCh.sendMessage(objUser.getAvatarUrl()).queue();
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "defaultavatar"))
			objMsgCh.sendMessage(objUser.getDefaultAvatarUrl()).queue();
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "nick"))
			objMsgCh.sendMessage(objMem.getEffectiveName()).queue();
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"招待"))
			objMsgCh.sendMessage(objMsg.getInvites().toString()).queue();
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"id"))
			objMsgCh.sendMessage("あなたのIDはこれ！\n```java\n" + objUser.getId() + "```").queue();

		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "SplashID"))			//SplashIDというものが取得できるものの、どうやったらSplashがデフォルトから変更できるのかがわからない
			objMsgCh.sendMessage(objGld.getSplashId()).queue();
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "SplashURL"))
			objMsgCh.sendMessage(objGld.getSplashUrl()).queue();
		String strGld = String.valueOf(objGld);										//このコードは単にオブジェクトをString型に変換する方法をメモしたかっただけ
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "ギルドオブジェクト"))
			objMsgCh.sendMessage(strGld).queue();

		if(objMsg.getContentRaw().equalsIgnoreCase("うんち"))
			objMsgCh.deleteMessageById(objMsgCh.getLatestMessageId());
			objMsgCh.sendMessage("おっと。" + objUser.getAsMention() + "さんの発言を消しておいたよ。");
			objMsgCh.deleteMessageById(objMsgCh.getLatestMessageId());

		//Invite
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"invite")) {
			objMsgCh.sendMessage("Hisuiの招待はこちら！：\nhttps://discordapp.com/oauth2/authorize?client_id=472320589433012226&permissions=8&scope=bot").queue();
			objMsgCh.sendMessage(objGld.getInvites().toString()).queue();
			objMsgCh.sendMessage(Route.Invites.GET_INVITE.toString()).queue();
		}

		//Report
		TextChannel reportChannel = objGld.getTextChannelById(500890040671666188L);
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "report")) {
			reportChannel.sendMessage(objUser.getAsMention() + "さんからの報告：\n" + objMsg.getContentDisplay());
			objMsgCh.sendMessage("報告が完了しました。ご協力ありがとうございます！").queue();
		}

		//Quiz
		Quiz quiz = new Quiz();
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "q"))
			quiz.play();

		//Audio
		AudioManager audioManager = objGld.getAudioManager();
		//AudioSendHandler handler = new AudioSendHandler();						//エラー出てるのでとりあえずコメント化
		long vcID = 483000262739492874l;
		VoiceChannel vc = objGld.getVoiceChannelById(vcID);
		AudioFormat INPUT_FORMAT = new AudioFormat(48000f, 16, 2, true, true);	//わけもわからずとりあえず追加してみた
		//audioManager.setSendingHandler(handler);									//エラー出てるのでとりあえずコメント化

		//VC
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "vc"))
			audioManager.openAudioConnection(vc);
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "vcout"))
			audioManager.closeAudioConnection();


		//Coin
		HashMap<String, Integer> hashmap =new HashMap<String, Integer>();
		int coin = 0;

		if(evt.getAuthor().isBot()) {
		}else {
			int x = 1;
			coin += x;
			if(hashmap.containsKey(objUser.getId())) {
				hashmap.put(objUser.getId(), coin);
			}
			//objMsgCh.sendMessage("コインが" + x + "枚増えたよ！").queue();		これはテスト用なのでコメント化
		}
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "coin")) {
			objMsgCh.sendMessage("きみは" + hashmap.get(objUser.getId()) + "枚のコインを持っているよ！").queue();
		}


		//Gacha
		Gacha gacha = new Gacha();
		gacha.doMethod(objMsg, objMsgCh);

		//EmbedTest
		EmbedBuilder eb = new EmbedBuilder();								//EmbedBuilderインスタンスを作る
		eb.setTitle("ここがタイトル", null)	;								//タイトルを設定。1つ目は文字列としてのタイトル、2つ目は文字列またはnullとしてのURL
		eb.setColor(Color.red);												//色を設定。
		eb.setDescription("本文");											//本文を設定。
		eb.addField("フィールドタイトル", "フィールド（？）の本文", false);	//埋め込みにFieldを追加する。1つ目は文字列としてタイトル、2つ目は文字列として本文、3つ目はinline modeというやつ。trueまたはfalse。
		eb.addBlankField(false);											//spacer like fieldを追加（なんだこれ）
		eb.setAuthor(objUser.getName(), null, null);
		//↑embed authorを追加。1つ目に文字列として名前、2つ目に文字列としてURL（またはnull）、3つ目に文字列としてアイコンのURL（またはnull）
		eb.setFooter("フッターのテスト", "https://github.com/zekroTJA/DiscordBot/blob/master/.websrc/zekroBot_Logo_-_round_small.png");
		//↑フッターの設定。1つ目に文字列として本文、2つ目に文字列としてアイコンのURL（またはnull）
		eb.setImage("http://tama-yura.jp/animetory/img/entries/7d22f228e7f4b94da61097c418823bcb121cdf69/567d0ddf90c73/pc/1.jpg.png");
		//↑画像の設定。文字列として画像のURL
		eb.setThumbnail("http://anihonetwallpaper.com/image/2017/12/43914-Re_Zero-iPhone.jpg.png");
		//↑サムネイル画像の設定。
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"embed"))
			objMsgCh.sendMessage(eb.build()).queue();

		//User
		EmbedBuilder ebuser = new EmbedBuilder();
		ebuser.setTitle("ユーザー情報", null)	;
		ebuser.setColor(Color.red);
		ebuser.setDescription(objUser.getAsMention()
								+ "**ユーザー名**：" + objUser.getName() + "#" +objUser.getDiscriminator()
								+ "\n\n**ニックネーム**：" + objMem.getEffectiveName()
								+ "\n\n**ユーザーID**：" + objUser.getId()
								+ "\n\n**プレイ中のゲーム**：" + objMem.getGame()
								+ "\n\n**ステータス**：" + objMem.getOnlineStatus()
								+ "\n\n**役職**：" + objMem.getRoles()
								+ "\n\n**パーミッション**：\n" + objMem.getPermissions()
								+ "\n\n**ボイスステータス**：" + objMem.getVoiceState()
								+ "\n\n**サーバー参加日時**：" + objMem.getJoinDate()
								+ "\n\n**アカウント作成日時**：" + objUser.getCreationTime());
		ebuser.addBlankField(false);
		ebuser.setThumbnail(objUser.getAvatarUrl());
		ebuser.setFooter("実行者：" + objUser.getName(), null);

		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "user"))
			objMsgCh.sendMessage(ebuser.build()).queue();


		//Channel
		EmbedBuilder ebch = new EmbedBuilder();
		ebch.setTitle("チャンネル情報", null)	;
		ebch.setColor(Color.red);
		ebch.setDescription("**チャンネル名**：" + objMsgCh.getName()
								+ "\n"
								+ "\n**チャンネルID**：" + objMsgCh.getId()
								+ "\n"
								+ "\nプライベートチャンネル：" + objMsg.getPrivateChannel()
								+ "\n"
								+ "\nチャンネル作成日時：" + objMsgCh.getCreationTime());
		ebch.addBlankField(false);
		ebch.setAuthor(objUser.getName(), null, null);
		ebch.setThumbnail(objUser.getAvatarUrl());


		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "channnel"))
			objMsgCh.sendMessage(ebch.build()).queue();


		//Guild
		EmbedBuilder ebgld = new EmbedBuilder();
		ebgld.setTitle("サーバー情報", null)	;
		ebgld.setColor(Color.red);
		ebgld.setDescription("サーバー名：" + objGld.getName()
								+ "\n"
								+ "\nオーナー：" + objGld.getOwner()
								+ "\n"
								+ "\nサーバーリージョン：" + objGld.getRegionRaw()
								+ "\n"
								+ "\nAFKチャンネル：" + objGld.getAfkChannel()
								+ "\n"
								+ "\nAFKタイムアウト時間：" + objGld.getAfkTimeout()
								+ "\n"
								+ "\nBANされたユーザー：" + objGld.getBanList()
								+ "\n"
								+ "\nカテゴリー一覧：" + objGld.getCategories()
								+ "\n"
								+ "\nコントローラー：" + objGld.getController()
								+ "\n"
								+ "\nサーバー作成日時：" + objGld.getCreationTime()
								+ "\n"
								+ "\nデフォルトチャンネル：" + objGld.getDefaultChannel()
								+ "\n"
								+ "\nデフォルト通知レベル：" + objGld.getDefaultNotificationLevel()
								+ "\n"
								+ "\n絵文字キャッシュ：" + objGld.getEmoteCache()
								+ "\n"
								+ "\nExplicit content level：" + objGld.getExplicitContentLevel()
								+ "\n"
								+ "\nフィーチャー：" + objGld.getFeatures()
								+ "\n"
								+ "\nマネージャー：" + objGld.getManager()
								+ "\n"
								+ "\nメンバー：" + objGld.getMember(objUser)
								+ "\n"
								+ "\nパブリックロール：" + objGld.getPublicRole()
								+ "\n"
								+ "\n役職：" + objGld.getRoles()
								+ "\n"
								+ "\nセルフメンバー：" + objGld.getSelfMember()
								+ "\n"
								+ "\nシステムチャンネル：" + objGld.getSystemChannel()
								+ "\n"
								+ "\nチャンネルのキャッシュ：" + objGld.getTextChannelCache()
								+ "\n"
								+ "\nRequired MFA Level：" + objGld.getRequiredMFALevel()
								+ "\n"
								+ "\nVerification level：" + objGld.getVerificationLevel()
								+ "\n"
								+ "\nボイスチャンネルのキャッシュ：" + objGld.getVoiceChannelCache()
								+ "\n"
								+ "\nボイスチャンネル：" + objGld.getVoiceChannels()
								+ "\n"
								+ "\nボイスステータス：" + objGld.getVoiceStates()
								+ "\n"
								+ "\nWebhook：" + objGld.getWebhooks());
		ebgld.addBlankField(false);
		ebgld.setAuthor("実行者：" + objUser.getName(), null, null);
		ebgld.setThumbnail(objGld.getIconUrl());


		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "guild"))
			objMsgCh.sendMessage(ebgld.build()).queue();


		//Member
		EmbedBuilder ebMem = new EmbedBuilder();
		ebMem.setTitle("ユーザー情報", null)	;
		ebMem.setColor(Color.green);
		ebMem.setDescription("名前：" + objMem.getAsMention()
								+ "\n"
								+ "\nニックネーム：" + objMem.getEffectiveName()
								+ "\n"
								+ "\nカラー：" + objMem.getColor()
								+ "\n"
								+ "\nプレイ中のゲーム：" + objMem.getGame()
								+ "\n"
								+ "\nアカウント作成日時：" + objUser.getCreationTime()
								+ "\n"
								+ "\nサーバー参加日時：" + objMem.getJoinDate()
								+ "\n"
								+ "\nステータス：" + objMem.getOnlineStatus()
								+ "\n"
								+ "\nパーミッション：" + objMem.getPermissions()
								+ "\n"
								+ "\n役職：" + objMem.getRoles()
								+ "\n"
								+ "\nボイスステータス：" + objMem.getVoiceState());
		ebMem.addBlankField(false);
		ebMem.setAuthor(objUser.getName(), null, null);
		ebMem.setThumbnail(objUser.getAvatarUrl());
		ebMem.appendDescription(objUser.getId());
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "user " + objMsg.getMentionedUsers().indexOf(0)))
			objMsgCh.sendMessage(ebMem.build()).queue();




		//BotStatus
		EmbedBuilder ebbot = new EmbedBuilder();
		ebbot.setTitle("bot情報", null)	;
		ebbot.setColor(Color.green);
		ebbot.setDescription(objMsg.getMentionedUsers() + "のステータス："
								+ "\n"
								+ "\nユーザーID：472320589433012226"
								+ "\n"
								+ "\nアカウント作成日時：");
		ebuser.addBlankField(false);
		ebuser.setAuthor(objUser.getName(), null, null);
		ebuser.setThumbnail(objUser.getAvatarUrl());
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "user"))
		objMsgCh.sendMessage(ebuser.build()).queue();
	}
}
