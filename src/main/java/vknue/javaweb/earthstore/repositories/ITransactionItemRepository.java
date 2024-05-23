package vknue.javaweb.earthstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vknue.javaweb.earthstore.models.Transaction;
import vknue.javaweb.earthstore.models.TransactionItem;

public interface ITransactionItemRepository extends JpaRepository<TransactionItem, Long>
{

}
