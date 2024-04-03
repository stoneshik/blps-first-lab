INSERT INTO tax_regime (
    id, title, description, taxpayer_category, tax_feature, max_annual_income_thousands, max_number_employees
) VALUES
    (default,
     'УСН (доходы) для ИП\n',
     'Необходимость регистрации в качестве ИП - Да\n' ||
     'Представление уведомления о переходе на УСН - Да (не позднее последнего числа года, предшествующего году, с которого планируется начать применение УСН)\n' ||
     'Налоговая ставка - 6% или 8% в зависимости от размера доходов и (или) средней численности работников (законами субъектов РФ ставка может быть снижена до 1%)\n' ||
     'Отчетность - Декларация\n' ||
     'Периодичность отчётов - Раз в год\n' ||
     'Периодичность уплаты налога - Авансовые платежи ежеквартально\n' ||
     'Основные ограничения:\n' ||
     '- по количеству работников (не более 130 человек)\n' ||
     '- по доходам за год (не более 200 млн. руб. в год)\n' ||
     'Ведение налогоплательщиком налогового учета - Книга учета доходов и расходов\n' ||
     'Налоговая база - Доход\n' ||
     'Региональные особенности - Законами субъектов РФ могут быть установлены налоговые ставки в пределах от 1 до 6% в зависимости от категорий налогоплательщиков',
     'INDIVIDUAL_ENTREPRENEUR',
     NULL,
     200000,
     130
     ),
    (default,
     'УСН (доходы минус расходы) для ИП',
     'Необходимость регистрации в качестве ИП - Да\n' ||
     'Представление уведомления о переходе на УСН - Да (не позднее последнего числа года, предшествующего году, с которого планируется начать применение УСН)\n' ||
     'Налоговая ставка - 15% или 20% в зависимости от размера доходов и (или) средней численности работников (законами субъектов РФ ставка может быть снижена до 5%)\n' ||
     'Отчетность - Декларация\n' ||
     'Периодичность отчётов - Раз в год\n' ||
     'Периодичность уплаты налога - Авансовые платежи ежеквартально\n' ||
     'Основные ограничения:\n' ||
     '- по количеству работников (не более 130 человек)\n' ||
     '- по доходам за год (не более 200 млн. руб. в год)\n' ||
     'Ведение налогоплательщиком налогового учета - Книга учета доходов и расходов\n' ||
     'Налоговая база - Доход минус расходы\n' ||
     'Региональные особенности - Законами субъектов РФ могут быть установлены налоговые ставки в пределах от 5 до 15 % в зависимости от категорий налогоплательщиков',
     'INDIVIDUAL_ENTREPRENEUR',
     NULL,
     200000,
     130
    ),
    (default,
     'ПСН',
     'Необходимость регистрации в качестве ИП - Да\n' ||
     'Необходимость подачи заявления на получение патента - Да\n' ||
     'Налоговая ставка - 6%\n' ||
     'Отчетность - Не представляется\n' ||
     'Срок на который может быть выдан патент (по выбору ИП) - От 1 до 12 месяцев\n' ||
     'Периодичность уплаты налога - Зависит от срока действия патента:\n' ||
     '- до 6 месяцев в размере полной суммы налога в срок не позднее срока окончания действия патента;\n' ||
     '- от 6 до 12 месяцев:\n' ||
     '- в размере 1/3 суммы налога в срок не позднее 90 календарных дней после начала действия патента;\n' ||
     '- в размере 2/3 суммы налога в срок не позднее срока окончания действия патента.\n' ||
     'Основные ограничения:\n' ||
     '- по количеству работников (не более 15 чел.)\n' ||
     '- по доходам за год (не более 60 млн руб. в год)\n' ||
     '- по видам деятельности (бытовые услуги, перевозки, розничная торговля, общепит)\n' ||
     'Ведение налогоплательщиком налогового учета - Книга учета доходов\n' ||
     'Налоговая база - Потенциально возможный доход (ожидаемый размер годового дохода)\n' ||
     'Региональные особенности - Законами РФ устанавливаются:\n' ||
     '- перечень видов деятельности\n' ||
     '- размеры потенциально возможного годового дохода',
     'INDIVIDUAL_ENTREPRENEUR',
     'NO_OBLIGATION_SUBMIT_DECLARATIONS',
     60000,
     15
     ),
    (default,
     'НПД',
     'Необходимость регистрации в качестве ИП - Нет\n' ||
     'Как стать налогоплательщиком налога на профессиональный доход - Зарегистрироваться в качестве налогоплательщика можно, скачав мобильное приложение «Мой налог» или через web-кабинет «Мой налог»\n' ||
     'Налоговая ставка - 4% при реализации товаров (работ, услуг) физическим лицам или 6% при реализации товаров (работ, услуг) ЮЛ и ИП\n' ||
     'Отчетность - Нет\n' ||
     'Периодичность уплаты налога - Ежемесячно\n' ||
     'Основные ограничения:\n' ||
     '- по доходам за год (не более 2,4 млн руб.)\n' ||
     '- отсутствие наемных работников\n' ||
     'Ведение налогоплательщиком налогового учета - Нет\n' ||
     'Налоговая база - Доход от реализации товаров (работ, услуг) за исключением доходов, полученных по трудовому договору\n' ||
     'Где можно применять налог на профессиональный доход - В настоящее время специальный налоговый режим "Налог на профессиональный доход" введен в 84 субъектах Российской Федерации.',
     'INDIVIDUAL',
     'NO_NEED_KEEP_TAX_RECORDS_AND_OBLIGATION_SUBMIT_DECLARATIONS',
     2400,
     0
    ),
    (default,
     'УСН (доходы минус расходы) для ЮЛ',
     'Представление уведомления о переходе на УСН - Да (не позднее последнего числа года, предшествующего году, с которого планируется начать применение УСН)\n' ||
     'Налоговая ставка - 6% или 8% в зависимости от размера доходов и (или) средней численности работников (законами субъектов РФ ставка может быть снижена до 1%)\n' ||
     'Отчетность - Декларация\n' ||
     'Периодичность отчётов - Раз в год\n' ||
     'Периодичность уплаты налога - Авансовые платежи ежеквартально\n' ||
     'Основные ограничения:\n' ||
     '- по количеству работников (не более 130 человек)\n' ||
     '- по доходам за год (не более 200 млн. руб. в год)\n' ||
     'Ведение налогоплательщиком налогового учета - Книга учета доходов и расходов\n' ||
     'Налоговая база - Доход\n' ||
     'Региональные особенности - Законами субъектов РФ могут быть установлены налоговые ставки в пределах от 1 до 6% в зависимости от категорий налогоплательщиков',
     'LEGAL_ENTITY',
     NULL,
     200000,
     130
    ),
    (default,
     'УСН (доходы) для ЮЛ',
     'Представление уведомления о переходе на УСН - Да (не позднее последнего числа года, предшествующего году, с которого планируется начать применение УСН)\n' ||
     'Налоговая ставка - 15% или 20% в зависимости от размера доходов и (или) средней численности работников (законами субъектов РФ ставка может быть снижена до 5%)\n' ||
     'Отчетность - Декларация\n' ||
     'Периодичность отчётов - Раз в год\n' ||
     'Периодичность уплаты налога - Авансовые платежи ежеквартально\n' ||
     'Основные ограничения:\n' ||
     '- по количеству работников (не более 130 человек)\n' ||
     '- по доходам за год (не более 200 млн. руб. в год)\n' ||
     'Ведение налогоплательщиком налогового учета - Книга учета доходов и расходов\n' ||
     'Налоговая база - Доход минус расходы\n' ||
     'Региональные особенности - Законами субъектов РФ могут быть установлены налоговые ставки в пределах от 5 до 15 % в зависимости от категорий налогоплательщиков',
     'LEGAL_ENTITY',
     NULL,
     200000,
     130
    ),
    (default,
     'ОРН для ЮЛ',
     'Наличие регистрации в качестве ЮЛ - Да\n' ||
     'Ставка налога - По налогу на прибыль организаций: 20%\n' ||
     'Отчетность - Декларация\n' ||
     'Периодичность отчётов - Первый квартал, полугодие, 9 месяцев (календарный год)\n' ||
     'Периодичность уплаты налога - Авансовые платежи ежеквартально\n' ||
     'Основные ограничения:\n' ||
     '- Ограничений нет\n' ||
     'Ведение налогоплательщиком налогового учета - Да\n' ||
     'Налоговая база - Доходы, уменьшенные на величину расходов\n' ||
     'Иные особенности:\n' ||
     '- Законами субъектов РФ могут быть установлены пониженные ставки налога\n' ||
     '- Признаются плательщиками НДС и налога на имущество организаций',
     'LEGAL_ENTITY',
     'PRODUCTION_EXCISABLE_GOODS',
     NULL,
     NULL
    ),
    (default,
     'ОРН для ИП',
     'Наличие регистрации в качестве ИП - Да\n' ||
     'Ставка налога - По налогу на доходы физических лиц: 13%\n' ||
     'Отчетность - Декларация\n' ||
     'Периодичность отчётов - Раз в год\n' ||
     'Периодичность уплаты налога - Авансовые платежи ежеквартально\n' ||
     'Основные ограничения - Ограничений нет\n' ||
     'Ведение налогоплательщиком налогового учета - Книга учета доходов, расходов и хозяйственных операций\n' ||
     'Налоговая база - Доходы, уменьшенные на величину расходов\n' ||
     'Иные особенности:\n' ||
     '- Признаются плательщиками НДС',
     'INDIVIDUAL_ENTREPRENEUR',
     'PRODUCTION_EXCISABLE_GOODS',
     NULL,
     NULL
    ),
    (default,
     'АВТОУСН',
     'Необходимость регистрации в качестве ИП - Да\n' ||
     'Как начать применять АУСН:\n' ||
     'Эксперимент проводится в г. Москве, Московской и Калужской областях и Республике Татарстан\n' ||
     'С 1 июля 2022 применять режим АУСН могут вновь зарегистрированные ИП и ЮЛ, подав уведомление не позднее 30 календарных дней с даты постановки на учет в налоговом органе.\n' ||
     'С 1 января 2023 года – для всех налогоплательщиков.\n' ||
     'Налогоплательщики, изъявившие желание перейти на АУСН со следующего календарного года, уведомляют об этом налоговый орган не позднее 31 декабря календарного года, предшествующего календарному году, начиная с которого налогоплательщики переходят на АУСН\n' ||
     'Подать уведомление можно в Личном кабинете налогоплательщика или через уполномоченный банк.\n' ||
     'Налоговая ставка:\n' ||
     '- 8% для объекта налогообложения «доходы»\n' ||
     '- 20% для объекта налогообложения «доходы, уменьшенные на величину расходов»\n' ||
     'Отчетность - Нет\n' ||
     'Периодичность уплаты налога - Ежемесячно\n' ||
     'Основные ограничения:\n' ||
     '- годовой доход не более 60 млн рублей\n' ||
     '- численность работников не более 5\n' ||
     'Ведение налогоплательщиком налогового учета - Учет доходов и расходов ведется автоматически в Личном кабинете АУСН\n' ||
     'Страховые взносы и фонды - Не уплачиваются',
     'INDIVIDUAL_ENTREPRENEUR_AND_LEGAL_ENTITY',
     'NO_NEED_KEEP_TAX_RECORDS_AND_OBLIGATION_SUBMIT_DECLARATIONS',
     60000,
     5
    )
    ;