package com.tistory.eclipse4j.core.config.db

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager

class RWRoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any {
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly())
            return DataSourceType.READ
        return DataSourceType.WRITE
    }
}
