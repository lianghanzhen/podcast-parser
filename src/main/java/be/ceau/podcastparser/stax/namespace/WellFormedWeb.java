/*
	Copyright 2017 Marceau Dewilde <m@ceau.be>
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
		https://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package be.ceau.podcastparser.stax.namespace;

import java.util.Set;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import be.ceau.podcastparser.stax.models.Item;
import be.ceau.podcastparser.stax.models.Link;
import be.ceau.podcastparser.util.UnmodifiableSet;

/**
 * <p>
 * The {@code <wfw:commentRss>} element lets you syndicate your comments. The
 * URI in the {@code <wfw:commentRss>} element must point to an RSS feed
 * containing the comments for the {@code <item>} it is contained in.
 * </p>
 * 
 * @see https://developer.mozilla.org/en-US/docs/Web/RSS/Module/Well-Formed_Web/Element
 */
public class WellFormedWeb implements Namespace {

	public static final Set<String> NAMES = UnmodifiableSet.of("http://wellformedweb.org/commentapi/");

	@Override
	public Set<String> getNames() {
		return NAMES;
	}

	@Override
	public void process(Item item, XMLStreamReader reader) throws XMLStreamException {
		switch (reader.getLocalName()) {
		case "comment": {
			// a link to directly POST comments about this item
			Link link = new Link();
			link.setHref(reader.getElementText());
			link.setRel("comment");
			item.addLink(link);
			break;
		}
		case "commentRss": {
			// a link to a feed of comments about this item
			Link link = new Link();
			link.setHref(reader.getElementText());
			link.setRel("commentRss");
			item.addLink(link);
			break;
		}
		}
		Namespace.super.process(item, reader);
	}

}