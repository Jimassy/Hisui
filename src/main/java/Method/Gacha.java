package Method;

import Basic.Ref;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

public class Gacha {
    public void doMethod(Message objMsg, MessageChannel objMsgCh) {
        // Gacha
        double random = Math.random();
        int die = 0;
        if (objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "gacha"))

            die = (int) (random * 6) + 1;

        switch (die) {

        case 1:
            objMsgCh.sendMessage("エミリア").queue();
            break;

        case 2:
            objMsgCh.sendMessage("レム").queue();
            break;

        case 3:
            objMsgCh.sendMessage("ラム").queue();
            break;

        case 4:
            objMsgCh.sendMessage("ベアトリス").queue();
            break;

        case 5:
            objMsgCh.sendMessage("ペトラ").queue();
            break;

        case 6:
            objMsgCh.sendMessage("ペテルギウス").queue();
            break;
        }
    }
}