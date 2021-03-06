package server.tradesource;

import org.specs.collection.JavaCollectionsConversion;
import scala.Enumeration;
import scala.collection.immutable.List;
import server.TradeRoutingActor;
import server.TradeMessage;
import server.model.BasketTrade;
import server.model.Underlying;

public class JavaTradeSource extends Thread {

    public JavaTradeSource() {
        Enumeration.Value[] underlyings = new Enumeration.Value[]{
                Underlying.HSBC()
        };


        List<Enumeration.Value> scalaUnderlyingsList = JavaCollectionsConversion.javaArrayToList(underlyings);

        TradeRoutingActor.$bang
                (new TradeMessage("JavaTradeSource", "J1", new BasketTrade("J11", scalaUnderlyingsList, 77.5)));

//        TradeRoutingActor$.$bang
  //              (new TradeMessage("JavaTradeSource", "J1", new TradeWithKnockout("J11", scalaUnderlyingsList, 77.5)));

    }

    /*class TradeWithKnockout extends BasketTrade implements HasKnockOut, HasBarrier, PriceChangeHandler{

        private boolean breached;

        public TradeWithKnockout(String id, List<Enumeration.Value> underlyings, double strikePrice) {
            super(id, underlyings, strikePrice);
        }

        @Override
        public void handlePriceChange(double price){
            HasKnockOut$class.handlePriceChange(this, price);
        }

        @Override
        public String description(){
            return HasKnockOut$class.description(this);
        }

        @Override
        public boolean breached(){
            return this.breached;
            //return HasBarrier$class.;
        }

        public void breached_$eq(boolean breached){
            this.breached = breached;
        }

        @Override
        public void act(){
            PriceChangeHandler$class.act(this);
        }

        @Override
        public void onTerminate(Function0<BoxedUnit> function){
            Actor$class.onTerminate(this, function);
        }

    }*/

}
