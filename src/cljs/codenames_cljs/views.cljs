(ns codenames-cljs.views
  (:require [re-frame.core :as re-frame]))

(defn cell [x y]
  (let [c (deref (re-frame/subscribe [:cell x y]))
        winner (re-frame/subscribe [:winner])
        word (:word c)]
    (fn []
      (if @winner
        [:span
         word]
        [:button {:on-click #(re-frame/dispatch [:move word])}
         word]))))

(defn grid []
  [:table
   (for [y (range 5)]
     [:tr
      (for [x (range 5)]
        [:td [cell x y]])])])

(defn main-panel []
  (let [game (re-frame/subscribe [:game])
        turn (re-frame/subscribe [:turn])
        winner (re-frame/subscribe [:winner])]
    (fn []
      [:div
       (if @winner
         [:div
          (clojure.string/capitalize (name @winner)) " is the winner."]
         [:div
          "It's " (name @turn) "'s turn."]) 
       [:center
        [:p
         [grid]]
        [:p
         [:button {:on-click #(re-frame/dispatch [:initialize-db])}
          "RESET"]]]
       [:p
        @game]])))
