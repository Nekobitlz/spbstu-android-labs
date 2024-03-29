/*
 * Copyright 2019, The Android Open Source Project
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

package com.example.android.lifecycles.step6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class SavedStateViewModel extends ViewModel {

    private static String EXTRA_NAME = "extra_name";

    private SavedStateHandle stateHandle;

    public SavedStateViewModel(SavedStateHandle stateHandle) {
        this.stateHandle = stateHandle;
    }

    // Expose an immutable LiveData
    LiveData<String> getName() {
        return stateHandle.getLiveData(EXTRA_NAME);
    }

    void saveNewName(String newName) {
        stateHandle.set(EXTRA_NAME, newName);
    }
}
