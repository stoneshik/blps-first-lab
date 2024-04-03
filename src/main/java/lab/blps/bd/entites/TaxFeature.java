package lab.blps.bd.entites;

public enum TaxFeature {
    // Производство подакцизных товаров
    PRODUCTION_EXCISABLE_GOODS,
    // Нет необходимости ведения налогового учета
    NO_NEED_KEEP_TAX_RECORDS,
    // Нет обязанности предоставлять декларации
    NO_OBLIGATION_SUBMIT_DECLARATIONS,
    // Нет необходимости ведения налогового учета и обязанности предоставлять декларации
    NO_NEED_KEEP_TAX_RECORDS_AND_OBLIGATION_SUBMIT_DECLARATIONS
}
