(ns codenames-cljs.views
    (:require [re-frame.core :as re-frame]))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])
        game (re-frame/subscribe [:game])]
    (fn []
      [:div "Hello from " @name])))
