(ns codenames-cljs.views
  (:require [re-frame.core :as re-frame]))

(defn cell [x y]
  (let [c (deref (re-frame/subscribe [:cell x y]))
        word (:word c)]
    [:button {:on-click #(re-frame/dispatch [:move word])}
     word]))

(defn grid []
  [:table
   (for [y (range 5)]
     [:tr
      (for [x (range 5)]
        [:td [cell x y]])])])

(defn main-panel []
  (let [game (re-frame/subscribe [:game])]
    (fn []
      [:div
       [:center
        [grid]
        [:p
         [:button {:on-click #(re-frame/dispatch [:initialize-db])}
          "RESET"]]]
       [:p
        @game]])))
