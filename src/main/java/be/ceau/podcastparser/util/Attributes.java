package be.ceau.podcastparser.util;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.stream.XMLStreamReader;

/**
 * Utility class for extracting and cleaning attribute values.
 */
public class Attributes {

	private static final Map<String, Attributes> CACHE = new ConcurrentHashMap<>();
	
	public static Attributes get(String localName) {
		return CACHE.computeIfAbsent(localName, x -> new Attributes(localName));
	}

	private final String localName;

	private Attributes(String localName) {
		this.localName = localName;
	}
	
	public Optional<String> from(XMLStreamReader reader) {
		String value = reader.getAttributeValue(null, localName);
		if (value != null) {
			value = value.trim();
			if (!value.isEmpty()) {
				return Optional.of(value);
			}
		}
		return Optional.empty();
	}

}