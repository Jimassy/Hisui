package Method;

import Basic.Ref;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class Initialize {
	 public void doMethod(Message objMsg, MessageChannel objMsgCh, Member objMem ,User objUser, Guild objGld) {
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

	 }
}
