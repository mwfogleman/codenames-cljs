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
  (let [game (re-frame/subscribe [:game])
        turn (re-frame/subscribe [:turn])]
    (fn []
      [:div
       [:p
        (str "It's " (name @turn) "'s turn!")]
       [:center
        [grid]
        [:p
         [:button {:on-click #(re-frame/dispatch [:initialize-db])}
          "RESET"]]]
       [:p
        @game]])))
