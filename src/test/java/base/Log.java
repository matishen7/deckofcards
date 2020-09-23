package base;

import Interfaces.ILog;
import pojo.Card;
import pojo.Deck_Response;

public class Log implements ILog {
    @Override
    public void Print(Deck_Response response) {
        System.out.println(response.isSuccess());
        System.out.println(response.getDeck_id());
        System.out.println(response.getRemaining());
        System.out.println(response.isShuffled());
        if (response.getCards()!=null)
        for (Card card: response.getCards()
             ) {
            System.out.println(card.getImage());
            System.out.println(card.getCode());
            System.out.println(card.getSuit());
            System.out.println(card.getValue());
        }
    }
}
