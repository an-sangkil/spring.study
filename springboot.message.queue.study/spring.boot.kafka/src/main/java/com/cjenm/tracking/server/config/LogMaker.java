package com.cjenm.tracking.server.config;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * <pre>
 * Class Name  : LogMaker.java
 * Description : logback Filter에서 사용하기 위한 Maker 정의
 *               fileLog를 요청 및 유형에 따라 분리시키기 위함
 *
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2018. 2. 21.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2018 by CJENM|MezzoMedia.Inc. All right reserved.
 * @since 2018. 2. 21.
 */
public class LogMaker {

  public static Marker accessMaker = MarkerFactory.getMarker(LogMakerCode.ACCESS_MAKER.name());
  public static Marker responseMaker = MarkerFactory.getMarker(LogMakerCode.RESPONSE_MAKER.name());

  public static final Marker FACEBOOK_LOG_MAKER = MarkerFactory.getMarker(LogMakerCode.FACEBOOK_CRAWLER.name());
  public static final Marker GOOGLE_LOG_MAKER = MarkerFactory.getMarker(LogMakerCode.GOOGLE_CRAWLER.name());

  /**
   * <pre>
   * Class Name  : LogMaker.java
   * Description : logbak 에서 사용될 Maker Name
   *
   * example : logger.debug(LogMaker.FACEBOOK_LOG_MAKER ,"AbstractResponseData = {}", response.content());
   *
   * </pre>
   */
  public enum LogMakerCode {
    ACCESS_MAKER, RESPONSE_MAKER, FACEBOOK_CRAWLER, GOOGLE_CRAWLER
  }
}
