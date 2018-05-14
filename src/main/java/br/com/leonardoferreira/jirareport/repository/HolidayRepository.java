package br.com.leonardoferreira.jirareport.repository;

import br.com.leonardoferreira.jirareport.domain.Holiday;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lferreira
 * @since 5/7/18 6:51 PM
 */
public interface HolidayRepository extends CrudRepository<Holiday, Long> {
    List<Holiday> findByProjectId(Long id);
}
