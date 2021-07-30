/*
 * Copyright (C) 2020-21 Application Library Engineering Group
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

package com.wajahatkarim3.easymoneywidgets.util;

import java.io.IOException;
import java.util.Optional;
import ohos.agp.components.Attr;
import ohos.agp.components.AttrSet;
import ohos.app.Context;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

/**
 * class for Util functions.
 */
public final class AttrUtil {

    private AttrUtil() {
    }

    /**
     * Getting boolean value from the mapped Attribute Set.
     *
     * @param attrSet The AttrSet object.
     * @param key The key value for the required attribute.
     * @param isDefValue Default boolean value.
     * @return The boolean value which is mapped to the key.
     */
    public static boolean getBooleanValue(AttrSet attrSet, String key, boolean isDefValue) {
        Optional<Attr> temp = attrSet.getAttr(key);
        return temp.map(Attr::getBoolValue).orElse(isDefValue);
    }

    /**
     * Getting String from the mapped Attribute Set.
     *
     * @param attrSet The AttrSet object.
     * @param key The key value for the required attribute.
     * @param defValue Default String.
     * @return The String which is mapped to the key.
     */
    public static String getStringValue(AttrSet attrSet, String key, String defValue) {
        Optional<Attr> temp = attrSet.getAttr(key);
        return temp.map(Attr::getStringValue).orElse(defValue);
    }

    /**
     * Getting key of the required element.
     *
     * @param context Context.
     * @param i The resource id.
     * @return The key at the resource id.
     */
    public static String getString(Context context, int i) {
        try {
            return context.getResourceManager().getElement(i).getString();
        } catch (IOException | WrongTypeException | NotExistException e) {
            e.printStackTrace();
        }
        return null;
    }
}