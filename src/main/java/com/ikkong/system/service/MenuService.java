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
package com.ikkong.system.service;

import com.ikkong.core.base.service.IService;
import com.ikkong.system.model.Menu;

public interface MenuService extends IService<Menu> {
	int findLastNum(String code);

	boolean isExistCode(String code);

	boolean updateStatus(String col, Object status);
}
