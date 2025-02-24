package io.github.intellij.dlanguage.errorreporting;

import com.intellij.diagnostic.IdeErrorsDialog;
import com.intellij.idea.IdeaLogger;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.application.ex.ApplicationInfoEx;
import com.intellij.openapi.diagnostic.ErrorReportSubmitter;
import com.intellij.openapi.diagnostic.IdeaLoggingEvent;
import com.intellij.openapi.diagnostic.SubmittedReportInfo;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.util.Consumer;
import de.halirutan.mathematica.errorreporting.IdeaInformationProxy;
import io.sentry.Sentry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Map;

/**
 * Created by francis on 10/29/2017.
 */
public class DErrorReporter extends ErrorReportSubmitter {

    public DErrorReporter() {
        Sentry.init("https://f948f2ace2c0452a88d3ff2bd6abd4be@sentry.io/1806295", new DlangSentryClientFactory(getPluginDescriptor()));
    }

    /**
     * @return an action text to be used in Error Reporter user interface, e.g. "Report to JetBrains".
     */
    @NotNull
    @Override
    public String getReportActionText() {
        return "Report to Plugin Developers";
    }

    @Override
    public boolean submit(@NotNull final IdeaLoggingEvent[] events,
                          @Nullable final String additionalInfo,
                          @NotNull final Component parentComponent,
                          @NotNull final Consumer<SubmittedReportInfo> consumer) {
//        try {
//            Sentry.init("https://f0a6a71038a645db865befe4d197def8:0df5947c823e4c2cab13ce2ace621f21@sentry.io/237092");
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }
        for (final IdeaLoggingEvent event : events) {
            Sentry.getContext().addExtra("Additional info:", additionalInfo);
            try {
                final PluginId pluginId = IdeErrorsDialog.findPluginId(event.getThrowable());

                if(pluginId != null) {
                    Sentry.getContext().addExtra("plugin id", pluginId.getIdString());
                }

                final Map<String, String> keyValuePairs = IdeaInformationProxy.getKeyValuePairs(
                                                                                    event.getThrowable(),
                                                                                    IdeaLogger.ourLastActionId,
                                                                                    ApplicationManager.getApplication(),
                                                                                    (ApplicationInfoEx) ApplicationInfo.getInstance(),
                                                                                    ApplicationNamesInfo.getInstance(),
                                                                                    super.getPluginDescriptor()
                                                                                );
                for (final String key : keyValuePairs.keySet()) {
                    Sentry.getContext().addExtra(key, keyValuePairs.get(key));
                }
            } catch (final Exception e) {
                Sentry.getContext().addExtra("getting plugin info failed", e);
            }

            ApplicationManager
                .getApplication()
                .invokeLater(() -> Sentry.capture(event.getThrowable()));
        }
        return true;

    }
}
