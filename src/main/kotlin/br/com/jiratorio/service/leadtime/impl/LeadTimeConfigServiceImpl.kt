package br.com.jiratorio.service.leadtime.impl

import br.com.jiratorio.domain.entity.LeadTimeConfig
import br.com.jiratorio.domain.request.LeadTimeConfigRequest
import br.com.jiratorio.domain.response.LeadTimeConfigResponse
import br.com.jiratorio.exception.ResourceNotFound
import br.com.jiratorio.extension.log
import br.com.jiratorio.mapper.toLeadTimeConfig
import br.com.jiratorio.mapper.toLeadTimeConfigResponse
import br.com.jiratorio.mapper.updateFromLeadTimeConfigRequest
import br.com.jiratorio.repository.LeadTimeConfigRepository
import br.com.jiratorio.service.BoardService
import br.com.jiratorio.service.leadtime.LeadTimeConfigService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LeadTimeConfigServiceImpl(
    private val leadTimeConfigRepository: LeadTimeConfigRepository,
    private val boardService: BoardService
) : LeadTimeConfigService {

    @Transactional(readOnly = true)
    override fun findAllByBoardId(boardId: Long): List<LeadTimeConfig> {
        log.info("Method=findAllByBoardId, boardId={}", boardId)

        return leadTimeConfigRepository.findByBoardId(boardId)
    }

    @Transactional(readOnly = true)
    override fun findAll(boardId: Long): List<LeadTimeConfigResponse> {
        log.info("Method=findAllByBoardId, boardId={}", boardId)

        return leadTimeConfigRepository
            .findByBoardId(boardId)
            .toLeadTimeConfigResponse()
    }

    @Transactional
    override fun create(boardId: Long, leadTimeConfigRequest: LeadTimeConfigRequest): Long {
        log.info("Method=create, boardId={}, leadTimeConfigRequest={}", boardId, leadTimeConfigRequest)

        val board = boardService.findById(boardId)
        val leadTimeConfig = leadTimeConfigRequest.toLeadTimeConfig(board)

        leadTimeConfigRepository.save(leadTimeConfig)

        return leadTimeConfig.id
    }

    @Transactional(readOnly = true)
    override fun findByBoardAndId(boardId: Long, id: Long): LeadTimeConfigResponse {
        log.info("Method=findByBoardAndId, boardId={}, id={}", boardId, id)

        val leadTimeConfig = leadTimeConfigRepository.findByIdAndBoardId(id, boardId)
            ?: throw ResourceNotFound()

        return leadTimeConfig.toLeadTimeConfigResponse()
    }

    @Transactional
    override fun update(boardId: Long, id: Long, leadTimeConfigRequest: LeadTimeConfigRequest) {
        log.info("Method=update, boardId={}, id={}, leadTimeConfigRequest={}", boardId, id, leadTimeConfigRequest)

        val leadTimeConfig = leadTimeConfigRepository.findByIdAndBoardId(id, boardId)
            ?: throw ResourceNotFound()

        leadTimeConfig.updateFromLeadTimeConfigRequest(leadTimeConfigRequest)
        leadTimeConfigRepository.save(leadTimeConfig)
    }

    @Transactional
    override fun deleteByBoardAndId(boardId: Long, id: Long) {
        log.info("Method=deleteByBoardAndId, boardId={}, id={}", boardId, id)

        val leadTimeConfig = leadTimeConfigRepository.findByIdAndBoardId(id, boardId)
            ?: throw ResourceNotFound()

        leadTimeConfigRepository.delete(leadTimeConfig)
    }

}
