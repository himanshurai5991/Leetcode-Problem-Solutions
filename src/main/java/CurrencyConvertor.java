
// [JPY-INR , 0.6]
// [USD-INR , 100] , [INR-USD , 0.01]
// JPY-USD = 0.6*0.01 = 6/1000 = 0.006

import kotlin.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.CompletableFuture;

interface ExchangeRateProvider {

    BigDecimal getExchangeRate(
            Currency source,
            Currency target
    );
}

public class CurrencyConvertor {

    ExchangeRateProvider exchangeRateProvider;
    private Map<Currency, List<ExchangeRate>> exchangeRatesByCurrency = new HashMap<>();

    public CurrencyConvertor(ExchangeRateProvider exchangeRateProvider) {
        this.exchangeRateProvider = exchangeRateProvider;
    }

    public Money convert(
            Money source,
            Currency targetCurrency
    ) throws Exception {
        // your implementation
        BigDecimal sourceAmount = source.amount;
        if (sourceAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if(source.currency.equals(targetCurrency)) {
            throw new IllegalArgumentException("Target currency should not be equal to source currency");
        }

        BigDecimal exchangeRate = exchangeRateProvider.getExchangeRate(source.currency, targetCurrency);


        if(exchangeRate == null) {
            exchangeRate = getExchangeRate(source.currency, targetCurrency, BigDecimal.ONE, new HashSet<>());
            if(exchangeRate == null) {
                exchangeRate = BigDecimal.ONE.divide(getExchangeRate(targetCurrency, source.currency, BigDecimal.ONE, new HashSet<>()), 20 , RoundingMode.HALF_UP);
            }

        }

        if(exchangeRate == null) {
            throw new Exception("Unable to convert currency " + source.currency + " to " + targetCurrency);
        }

        exchangeRatesByCurrency.put(source.currency,
                exchangeRatesByCurrency.getOrDefault(source.currency, new ArrayList<>())).add(new ExchangeRate(targetCurrency, exchangeRate));
        exchangeRatesByCurrency.put(targetCurrency,
                exchangeRatesByCurrency.getOrDefault(targetCurrency, new ArrayList<>())).add(new ExchangeRate(source.currency, BigDecimal.ONE.divide(exchangeRate, 20, RoundingMode.HALF_UP)));

        return new Money(targetCurrency,source.amount.multiply(exchangeRate));



    }

    BigDecimal getExchangeRate(
            Currency currentCurrency,
            Currency targetCurrency, BigDecimal currentRate, Set<Currency> visitedCurrency
    ) {

        if(currentCurrency.equals(targetCurrency)) {
            return currentRate;
        }

        visitedCurrency.add(currentCurrency);

        BigDecimal bestExchangeRate = null;
        List<ExchangeRate> rates = exchangeRatesByCurrency.getOrDefault(currentCurrency, Collections.emptyList());
        for(ExchangeRate rate : rates) {
            if(!visitedCurrency.contains(rate.targetCurrency)) {
                BigDecimal returnedRate = getExchangeRate(rate.targetCurrency, targetCurrency, currentRate.multiply(rate.amount), visitedCurrency);
                if(returnedRate != null && (bestExchangeRate == null || returnedRate.compareTo(bestExchangeRate) > 0)) {
                    bestExchangeRate = returnedRate;
                }
            }
        }
        visitedCurrency.remove(currentCurrency);

        return bestExchangeRate;

    }

    CompletableFuture<PaymentResult> getPaymentResult(Order order, PaymentService paymentService) {

        CompletableFuture<PaymentResult> paymentFuture =
                CompletableFuture.supplyAsync(
                        () -> paymentService.check(order)
                );
        return paymentFuture;
    }

    CompletableFuture<FraudResult> getFraudResult(Order order, FraudService fraudService) {
        CompletableFuture<FraudResult> fraudFuture =
                CompletableFuture.supplyAsync(
                        () -> fraudService.check(order)
                );
        return fraudFuture;

    }

    CompletableFuture<InventoryResult> getInventoryResult(Order order, InventoryService inventoryService) {
        CompletableFuture<InventoryResult> inventoryFuture =
                CompletableFuture.supplyAsync(
                        () -> inventoryService.check(order)
                );
        return inventoryFuture;
    }

    void makeApiCalls(Order order, PaymentService paymentService, FraudService fraudService, InventoryService inventoryService) {
        CompletableFuture<PaymentResult> paymentFuture = getPaymentResult(order, paymentService);
        CompletableFuture<FraudResult> fraudFuture = getFraudResult(order, fraudService);
        CompletableFuture<InventoryResult> inventoryFuture = getInventoryResult(order, inventoryService);



        try {
            CompletableFuture<Void> allFutures = CompletableFuture.allOf(paymentFuture, fraudFuture, inventoryFuture);

            allFutures.join();
            PaymentResult paymentResult = paymentFuture.join();
            FraudResult fraudResult = fraudFuture.join();
            InventoryResult inventoryResult = inventoryFuture.join();

            System.out.println("Payment Result: " + paymentResult.message);
            System.out.println("Fraud Result: " + fraudResult.message);
            System.out.println("Inventory Result: " + inventoryResult.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

class Money {
    Currency currency;
    BigDecimal amount;
    Money(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }
}


class ExchangeRate {

    Currency targetCurrency;
    BigDecimal amount;

    ExchangeRate(Currency targetCurrency, BigDecimal amount) {
        this.targetCurrency = targetCurrency;
        this.amount = amount;
    }
}


interface PaymentService {
    PaymentResult check(Order order);
}

interface FraudService {
    FraudResult check(Order order);
}

interface InventoryService {
    InventoryResult check(Order order);
}

class PaymentResult {
    boolean isSuccess;
    String message;

    PaymentResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}

class FraudResult {
    boolean isSuccess;
    String message;

    FraudResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}

class InventoryResult {
    boolean isSuccess;
    String message;

    InventoryResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
