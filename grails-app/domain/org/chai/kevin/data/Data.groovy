package org.chai.kevin.data;

/*
 * Copyright (c) 2011, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import groovy.transform.EqualsAndHashCode;

import java.util.Date
import java.util.Set

import org.chai.kevin.Period
import org.chai.location.DataLocationType
import org.chai.kevin.value.DataValue


@i18nfields.I18nFields
@EqualsAndHashCode(includes='code')
abstract class Data<T extends DataValue> {
	
	Long id
	
	Date timestamp = new Date()
	Date lastValueChanged = new Date()
	
	String code
	String names
	String descriptions
	
	static i18nFields = ['names', 'descriptions']
	
	static mapping = {
		table 'dhsst_data'
		tablePerHierarchy false
//		timestamp sqlType: "datetime"
//		lastValueChanged sqlType: "datetime"
		code unique: true
		cache true
	}
	
	static constraints =  {
		code (nullable: false, blank: false, unique: true)
		names (nullable: true)
		descriptions (nullable: true)
		
		lastValueChanged(nullable: true)
	}
	
	public abstract Type getType();
	
	public abstract Class<T> getValueClass();	
	
	public abstract Set<String> getSources(Period period, DataLocationType type);
	
	public abstract Set<String> getSources();
	
}