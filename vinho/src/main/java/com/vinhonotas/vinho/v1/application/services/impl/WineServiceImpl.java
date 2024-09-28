//package com.vinhonotas.vinho.v1.application.services.impl;
//
//import com.vinhonotas.vinho.v1.application.converters.WineConverter;
//import com.vinhonotas.vinho.v1.application.services.WineService;
//import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
//import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
//import com.vinhonotas.vinho.domain.entities.exceptions.exceptions.WineNotFoundException;
//import com.vinhonotas.vinho.v1.infraestructure.CreateWineRepository;
//import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
//import com.vinhonotas.vinho.v1.utils.MessagesConstants;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class WineServiceImpl implements WineService {
//
//    private final CreateWineRepository wineRepository;
//    private final WineConverter wineConverter;
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public WineEntity create(WineInputDTO wineInputDTO) {
//        log.info("create :: Registrando um novo vinho com os dados: {}", wineInputDTO.toString());
//        try {
//        return wineRepository.save(wineConverter.toEntity(wineInputDTO));
//        } catch (Exception e) {
//            log.error("create :: Ocorreu um erro: {} ", MessagesConstants.ERROR_CREATE_WINE, e);
//            throw new BadRequestException(MessagesConstants.ERROR_CREATE_WINE);
//        }
//    }
//
//    @Override
//    public List<WineEntity> getAll() {
//        log.info("getAll :: Listando todos os vinhos");
//        List<WineEntity> wineList = wineRepository.findAll();
//        if (wineList.isEmpty()) {
//            log.error("getAll :: Ocorreu um erro ao listar os vinhos: {} ", MessagesConstants.ERROR_WINE_NOT_FOUND);
//            throw new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND);
//        }
//        return wineList;
//    }
//
//    @Override
//    public WineEntity getById(UUID id) {
//        log.info("getById :: Buscando vinho pelo id: {}", id.toString());
//       return wineRepository.findById(id)
//               .orElseThrow(() -> new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND));
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public WineEntity update(UUID id, WineInputDTO wineInputDTO) {
//        log.info("update :: Atualizando vinho com os dados: {}", wineInputDTO.toString());
//        try {
//            WineEntity wineSaved = this.getById(id);
//            return wineRepository.save(wineConverter.toEntityUpdate(wineSaved, id, wineInputDTO));
//        } catch (Exception e) {
//            log.error("update :: Ocorreu um erro: {} ", MessagesConstants.ERROR_UPDATE_WINE_DATA, e);
//            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_WINE_DATA);
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(UUID id) {
//        log.info("delete :: Deletando vinho pelo id: {}", id.toString());
//        Optional<WineEntity> wine = wineRepository.findById(id);
//        if (wine.isEmpty()) {
//            log.error("delete :: Ocorreu um erro ao deletar o vinho: {} ", MessagesConstants.ERROR_WINE_NOT_FOUND);
//            throw new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND);
//        }
//        try {
//            wineRepository.deleteById(id);
//        } catch (Exception e) {
//            log.error("delete :: Ocorreu um erro ao deletar o vinho: {} ", MessagesConstants.ERROR_DELETE_WINE, e);
//            throw new BadRequestException(MessagesConstants.ERROR_DELETE_WINE);
//        }
//    }
//
//}
