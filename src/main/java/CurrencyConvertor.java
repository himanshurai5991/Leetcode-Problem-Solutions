
// [JPY-INR , 0.6]
// [USD-INR , 100]
//

import java.math.BigDecimal;
import java.util.Currency;

interface ExchangeRateProvider {

    BigDecimal getExchangeRate(
            Currency source,
            Currency target
    );
}

public class CurrencyConvertor {

    ExchangeRateProvider exchangeRateProvider;

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
        return new Money(targetCurrency,source.amount.multiply(exchangeRate));
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


