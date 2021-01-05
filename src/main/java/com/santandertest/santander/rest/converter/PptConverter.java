package com.santandertest.santander.rest.converter;

import com.santandertest.santander.rest.external.MovieDbApiClient;
import com.santandertest.santander.rest.model.MovieSearchResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PptConverter {

  private static final Logger log = LoggerFactory.getLogger(MovieDbApiClient.class);

  public void generatePpt(String fileName, List<MovieSearchResult> movieSearchResult) {
    XMLSlideShow ppt = new XMLSlideShow();

    for(MovieSearchResult movie : movieSearchResult) {
      XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
      XSLFSlideLayout layout = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
      XSLFSlide slide = ppt.createSlide(layout);

      XSLFTextShape titleShape = slide.getPlaceholder(0);
      titleShape.clearText();
      XSLFTextParagraph title = titleShape.addNewTextParagraph();
      XSLFTextRun titleRunner = title.addNewTextRun();
      titleRunner.setText(movie.getTitle());

      XSLFTextShape contentShape = slide.getPlaceholder(1);
      contentShape.clearText();
      XSLFTextParagraph content = contentShape.addNewTextParagraph();
      XSLFTextRun contentRunner = content.addNewTextRun();
      contentRunner.setText("Director: " + movie.getDirectorName() + "\n" +
                            "Released Year: " + movie.getReleaseDate());
    }

    writePptToDisk(fileName, ppt);
  }

  private void writePptToDisk(String fileName, XMLSlideShow ppt) {
    try (FileOutputStream out = new FileOutputStream(fileName + ".pptx")) {
      ppt.write(out);
    } catch (IOException e) {
      log.error(String.format("Failed to write PPT %s to disk", fileName), e);
    }
  }

}
