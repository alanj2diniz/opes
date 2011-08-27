/*
    This file is part of Opes.

    Opes is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Opes is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Opes.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.insula.opes;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.insula.opes.util.Assert;

public class Email implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";

	private static final String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)*";

	private static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

	private static final Pattern pattern = Pattern.compile("^" + ATOM + "+(\\." + ATOM + "+)*@" + DOMAIN + "|"
			+ IP_DOMAIN + ")$", Pattern.CASE_INSENSITIVE);

	private final String value;

	private Email(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Email) {
			Email other = (Email) obj;
			return new EqualsBuilder().append(this.value, other.value).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.value).toHashCode();
	}

	@Override
	public String toString() {
		return value;
	}

	public static Email fromString(String s) {
		Assert.notNull(s);
		Matcher m = pattern.matcher(s);
		Assert.isTrue(m.matches());

		return new Email(s);
	}

}