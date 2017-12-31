/*
	Copyright 2018 Marceau Dewilde <m@ceau.be>
	
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
package be.ceau.podcastparser.namespace.impl;

import java.util.Set;

import javax.xml.stream.XMLStreamException;

import be.ceau.podcastparser.PodParseContext;
import be.ceau.podcastparser.namespace.Namespace;
import be.ceau.podcastparser.util.UnmodifiableSet;

/**
 * The OpenSearch response elements can be used by search engines to augment
 * existing XML formats with search-related metadata.
 * 
 * OpenSearch response elements are typically found augmenting search results
 * returned in list-based XML syndication formats, such as RSS 2.0 and Atom 1.0,
 * but may be used in other contexts without restriction.
 * 
 * @see http://www.opensearch.org/Specifications/OpenSearch/1.1
 */
public class OpenSearch implements Namespace {

	private static final String NAME = "http://a9.com/-/spec/opensearchrss/1.1/";
	private static final Set<String> ALTERNATIVE_NAMES = UnmodifiableSet.of("http://a9.com/-/spec/opensearch/1.0/");

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public Set<String> getAlternativeNames() {
		return ALTERNATIVE_NAMES;
	}

	@Override
	public void process(PodParseContext ctx) throws XMLStreamException {
		switch (ctx.getReader().getLocalName()) {
		case "totalResults":
			// The number of search results available for the current search.
		case "startIndex":
			// The index of the first search result in the current set of search results.
		case "itemsPerPage":
			// The number of search results returned per page.
		default : 
			Namespace.super.process(ctx);
			break;
		}
	}

}

/*

	corpus stats
	
      6567 	--> http://a9.com/-/spec/opensearchrss/1.0/ level=feed localName=totalResults attributes=[]]
      6567 	--> http://a9.com/-/spec/opensearchrss/1.0/ level=feed localName=startIndex attributes=[]]
      6563 	--> http://a9.com/-/spec/opensearchrss/1.0/ level=feed localName=itemsPerPage attributes=[]]
       104 	--> http://a9.com/-/spec/opensearch/1.1/ level=feed localName=startIndex attributes=[]]
       100 	--> http://a9.com/-/spec/opensearch/1.1/ level=feed localName=totalResults attributes=[]]
        97 	--> http://a9.com/-/spec/opensearch/1.1/ level=feed localName=itemsPerPage attributes=[]]

*/