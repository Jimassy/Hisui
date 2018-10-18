package Method;

import java.awt.Color;

import Basic.Ref;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class Guilds {
	public void doMethod(Guild objGld, User objUser,Message objMsg, MessageChannel objMsgCh) {
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
	}
}
