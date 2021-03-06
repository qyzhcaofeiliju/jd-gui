/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package org.jd.gui.service.preferencespanel

import groovy.transform.CompileStatic
import org.jd.gui.spi.PreferencesPanel

import javax.swing.*
import java.awt.*

/**
 * Single instance is the default mode on Mac OSX, so this panel is not activated.
 */
@CompileStatic
class UISingleInstancePreferencesProvider extends JPanel implements PreferencesPanel {

    static final String SINGLE_INSTANCE = 'UIMainWindowPreferencesProvider.singleInstance'

    JCheckBox singleInstanceTabsCheckBox

    UISingleInstancePreferencesProvider() {
        super(new GridLayout(0,1))

        singleInstanceTabsCheckBox = new JCheckBox('Single instance')

        add(singleInstanceTabsCheckBox)
    }

    // --- PreferencesPanel --- //
    String getPreferencesGroupTitle() { 'User Interface' }
    String getPreferencesPanelTitle() { 'Main window' }

    public void init(Color errorBackgroundColor) {}

    public boolean isActivated() {
        !System.getProperty('os.name').toLowerCase().contains('mac os')
    }

    void loadPreferences(Map<String, String> preferences) {
        singleInstanceTabsCheckBox.selected = 'true'.equals(preferences.get(SINGLE_INSTANCE))
    }

    void savePreferences(Map<String, String> preferences) {
        preferences.put(SINGLE_INSTANCE, Boolean.toString(singleInstanceTabsCheckBox.selected))
    }

    boolean arePreferencesValid() { true }

    void addPreferencesChangeListener(PreferencesPanel.PreferencesPanelChangeListener listener) {}
}
