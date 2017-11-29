package com.algaworks.brewer.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.storage.FotoStorage;

public class CervejaEntityListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@PostLoad // toda vez que acabar de carregar a cerveja do banco
	public void postLoad(final Cerveja cerveja) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); //vai resolver as injeções de dependencias (FotStorage) com base no contexto
		cerveja.setUrlFoto(fotoStorage.getUrl(cerveja.getFotoOuMock()));
		cerveja.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + cerveja.getFotoOuMock()));
	}
	
}
