(ns codenames-cljs.views
  (:require [re-frame.core :as re-frame]))

(defn colorize [word identity]
  (case identity
    :blue [:div {:style {:color "blue"}}
           word]
    :red [:div {:style {:color "red"}}
          word]
    :assassin [:div {:style {:color "grey"}}
               word]
    :neutral [:div {:style {:color "black"}}
              word]))

(defn cell [x y]
  (let [c (deref (re-frame/subscribe [:cell x y]))
        winner (re-frame/subscribe [:winner])
        word (:word c) ;; probably need more subscribes here
        identity (:identity c)
        revealed (:revealed? c)]
    (fn []
      (if @winner
        [:span
         [colorize word identity]]
        [:button {:on-click #(re-frame/dispatch [:move word])}
         [colorize word identity]]))))

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
