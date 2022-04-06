package ua.ozzy.apiback.bitrix.format;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public abstract class AbstractParamKeysFormatter implements ParamKeysFormatter {

    @Override
    public Map<String, Object> format(Map<String, Object> params) {
        return params.entrySet().stream()
                .map(entry -> new Object() {
                    final String key = formatKey(entry.getKey());
                    final Object value = entry.getValue();
                }).collect(toMap(obj -> obj.key, obj -> obj.value));
    }

    protected abstract String formatKey(String key);

}
