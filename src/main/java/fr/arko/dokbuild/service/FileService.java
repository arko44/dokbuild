package fr.arko.dokbuild.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.arko.dokbuild.domain.Cards;

@Transactional(readOnly = true)
@Service
public class FileService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

	/**
	 * try to download thumb for all cards in list if file not exist
	 * @param list The cards
	 */
	public void downloadThumbsIfNotExist(List<Cards> list) {
		String thumbRoot = "D:/projets/repository/dokbuild/webapp/images/thumbs/";
		
		list.forEach(x -> {
			try {
				String newFilePath = thumbRoot + x.getId() + "_thumb.png";
				Path path = Paths.get(newFilePath);
				if (!path.toFile().exists()) {
					LOGGER.error("File " + x.getId() + " not exist");
					String fileUrl = "http://images.dokkan.info/images/card_thumbs_full/small/card_" + x.getId() + "_thumb_full.png";
					downloadUsingNIO(fileUrl, newFilePath);
				}
			} catch (Exception e) {
				
			}
		});
	}

	/**
	 * Download distant file
	 * @param urlStr
	 * @param file
	 * @throws IOException
	 */
	private void downloadUsingNIO(String urlStr, String file) throws IOException {
		URL url = new URL(urlStr);
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;
		try {
			rbc = Channels.newChannel(url.openStream());
			fos = new FileOutputStream(file);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} finally {
			if (fos != null) {
				fos.close();
			}
			if (rbc != null) {
				rbc.close();
			}
		}
	}
}
