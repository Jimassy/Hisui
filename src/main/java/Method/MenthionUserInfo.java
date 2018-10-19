package Method;

import java.awt.Color;

import Basic.Ref;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class MenthionUserInfo {
	public void doMethod(Message objMsg,User objUser,MessageChannel objMsgCh) {
		String menthionedMemberString = null;
		Member menthionedMember = null;
		for(Member member : objMsg.getMentionedMembers()) {
			if(member != null) {
				menthionedMemberString = member.getUser().getId().toString();
				menthionedMemberString = "<@" + menthionedMemberString +">";
				menthionedMember = member;
				break;
			}
		}
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "user " + menthionedMemberString)) {
			EmbedBuilder ebMem = new EmbedBuilder();
			ebMem.setTitle("ユーザー情報", null)	;
			ebMem.setColor(Color.green);
			ebMem.setDescription("名前：" + menthionedMember.getAsMention()
									+ "\n"
									+ "\nニックネーム：" + menthionedMember.getEffectiveName()
									+ "\n"
									+ "\nカラー：" + menthionedMember.getColor()
									+ "\n"
									+ "\nプレイ中のゲーム：" + menthionedMember.getGame()
									+ "\n"
									+ "\nアカウント作成日時：" + menthionedMember.getUser().getCreationTime()
									+ "\n"
									+ "\nサーバー参加日時：" + menthionedMember.getJoinDate()
									+ "\n"
									+ "\nステータス：" + menthionedMember.getOnlineStatus()
									+ "\n"
									+ "\nパーミッション：" + menthionedMember.getPermissions()
									+ "\n"
									+ "\n役職：" + menthionedMember.getRoles()
									+ "\n"
									+ "\nボイスステータス：" + menthionedMember.getVoiceState());
			ebMem.addBlankField(false);
			ebMem.setAuthor(objUser.getName(), null, null);
			ebMem.setThumbnail(objUser.getAvatarUrl());
			ebMem.appendDescription(objUser.getId());

				objMsgCh.sendMessage(ebMem.build()).queue();
		}
	}
}
