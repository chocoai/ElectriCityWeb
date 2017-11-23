/**
 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ikkong.core.toolbox.file;

import java.io.File;

import com.ikkong.core.constant.Cst;

public class FileProxyManager {
	private IFileProxy defaultFileProxyFactory = Cst.me().getDefaultFileProxyFactory();

	private static FileProxyManager me = new FileProxyManager();

	public static FileProxyManager me() {
		return me;
	}

	public IFileProxy getDefaultFileProxyFactory() {
		return defaultFileProxyFactory;
	}

	public void setDefaultFileProxyFactory(IFileProxy defaultFileProxyFactory) {
		this.defaultFileProxyFactory = defaultFileProxyFactory;
	}

	public String path(File file) {
		return defaultFileProxyFactory.path(file);
	}

	public String virtualPath(File file) {
		return defaultFileProxyFactory.virtualPath(file);
	}

	public File rename(File file) {
		return defaultFileProxyFactory.rename(file);
	}

}