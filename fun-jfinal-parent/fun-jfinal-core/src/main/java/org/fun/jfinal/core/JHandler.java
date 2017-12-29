/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 *
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fun.jfinal.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fun.jfinal.common.FileUtils;
import org.fun.jfinal.router.RouterManager;

import com.jfinal.handler.Handler;
import com.jfinal.kit.HandlerKit;

public class JHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		if (target.startsWith("/websocket")) {
			return;
		}
		
		String CPATH = request.getContextPath();
		request.setAttribute("REQUEST", request);
		request.setAttribute("CPATH", CPATH);
		request.setAttribute("SPATH", CPATH + "/static");
		request.setAttribute("JPRESS_VERSION", Jfun.VERSION);

		// 程序还没有安装
			if (target.indexOf('.') != -1) {
				return;
			}

		if (isDisableAccess(target)) {
			HandlerKit.renderError404(request, response, isHandled);
		}

		String originalTarget = target;
		target = RouterManager.converte(target, request, response);

		if (!originalTarget.equals(target)) {
			request.setAttribute("_original_target", originalTarget);
		}

		next.handle(target, request, response, isHandled);

	}

	private static boolean isDisableAccess(String target) {
		// 防止直接访问模板文件
		if (target.endsWith(".html") && target.startsWith("/templates")) {
			return true;
		}
		// 防止直接访问jsp文件页面
		if (".jsp".equalsIgnoreCase(FileUtils.getSuffix(target))) {
			return true;
		}

		return false;
	}

}
