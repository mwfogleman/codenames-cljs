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
  (let [c               (deref (re-frame/subscribe [:cell x y]))
        winner          (re-frame/subscribe [:winner])
        view            (re-frame/subscribe [:view])
        revealed-status (re-frame/subscribe [:revealed])
        word            (:word c) ;; probably need more subscribes here
        identity        (:identity c)]
    (fn []
      (if @winner
        [:span
         [colorize word identity]]
        (if (true? @revealed-status)
          [:span {:style {:width 30
                          :height 30}}
           [colorize word identity]]  
          [:button {:on-click #(re-frame/dispatch [:move word])
                    :style {:width 100
                            :height 100}}
           [colorize word identity]])))))

(defn grid []
  [:table
   (for [y (range 5)]
     [:tr
      (for [x (range 5)]
        [:td {:style {:width 100
                      :height 100
                      :text-align :center}}
         [cell x y]])])])

(defn main-panel []
  (let [game   (re-frame/subscribe [:game])
        turn   (re-frame/subscribe [:turn])
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
