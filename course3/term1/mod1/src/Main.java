import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

class CShares {
    String CompanyName;
    int SharesCount;
    int SharesPrice;

    public CShares(String _CompanyName, int _SharesCount, int _SharesPrice)
    {
        CompanyName = _CompanyName;
        SharesCount = _SharesCount;
        SharesPrice = _SharesPrice;

        if (SharesCount <= 0) {
            SharesCount = 0;
            SharesPrice = Main.INVALID_PRICE;
        }
    }

    synchronized public int PerformDeal(int _SharesCount) {
        int OldPrice = SharesPrice;

        if (SharesCount < _SharesCount) {
            return Main.INVALID_PRICE;
        } else if (SharesCount == _SharesCount) {
            SharesCount = 0;
        } else {
            SharesPrice = (SharesPrice * (SharesCount == 0 ? 1 : SharesCount)) / (SharesCount - _SharesCount);
            SharesCount -= _SharesCount;
        }

        System.out.println(CompanyName + ": " + Math.abs(_SharesCount) + (_SharesCount > 0 ? " bought": " sold") +
                           "\nOld price is " + OldPrice + ", new price is " + SharesPrice + "\n");
        int SharesPriceDelta = OldPrice * _SharesCount;
        Main.SharesChanged(SharesPriceDelta);
        return SharesPriceDelta;
    }

    public int SummaryPrices() {
        return SharesPrice * SharesCount;
    }
}

class CBuyer extends Thread {
    @Override
    public void run() {
        while (!interrupted()) {
            int SharesPos = (int)(Math.random() * Main.Shares.size());
            int SharesCount = (int)(Math.random() * 100) - 50;

            Main.Shares.get(SharesPos).PerformDeal(SharesCount);
        }
    }
}

public class Main {

    static final int INVALID_PRICE = -1;

    static Integer ExchangeIndex = 100;
    static ArrayList<CShares> Shares = new ArrayList<CShares>();
    static CountDownLatch Latch = new CountDownLatch(1);

    public static void main(String[] args) {
        Shares.add(new CShares("Tesla", 100, 1000));
        Shares.add(new CShares("Microsoft", 120, 900));
        Shares.add(new CShares("Google", 90, 1200));

        ArrayList<CBuyer> Buyers = new ArrayList<CBuyer>();
        for (int i = 0; i < 3; ++i)
            Buyers.add(new CBuyer());
        for (CBuyer Buyer : Buyers)
            Buyer.start();
        try {
            Latch.await();
        } catch (InterruptedException e) {}
        finally {
            for (CBuyer Buyer : Buyers)
                Buyer.interrupt();
        }
    }

    static public void SharesChanged(int _SharesPriceDelta) {
        int SummaryPrices = SummaryPrices();
        synchronized (ExchangeIndex) {
            int OldIndex = ExchangeIndex;
            ExchangeIndex = (OldIndex * SummaryPrices) / (SummaryPrices - _SharesPriceDelta);
            System.out.println("Old exchange index is " + OldIndex + ", new is " + ExchangeIndex + "\n");
            if (ExchangeIndex < 50) {
                System.out.println("Exchange index is lower than 50");
                Latch.countDown();
            }
        }
    }
    
    static public int SummaryPrices() {
        int Sum = 0;
        for (CShares Share : Shares) {
            Sum += Share.SummaryPrices();
        }
        return Sum;
    } 
}
