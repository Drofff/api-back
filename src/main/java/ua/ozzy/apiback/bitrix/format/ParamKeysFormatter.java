package ua.ozzy.apiback.bitrix.format;

import java.util.Map;

public interface ParamKeysFormatter {

    Map<String, Object> format(Map<String, Object> params);

}
