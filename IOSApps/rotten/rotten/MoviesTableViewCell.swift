//
//  MoviesTableViewCell.swift
//  rotten
//
//  Created by Praful Mantale on 12/2/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class MoviesTableViewCell: UITableViewCell {

   
    @IBOutlet weak var rateView: UIView!
    @IBOutlet weak var rcView: UIView!
    @IBOutlet weak var labelOne: UILabel!
    
    @IBOutlet weak var rv: UIView!
    var pv : PositionStatusView!
    var position : Double = 0
   
    @IBAction func onButton(sender: UIButton) {
        
        pv.setCurrentPosition(position)
        position -= 10
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
        rv.layer.cornerRadius = 5
        rateView.layer.cornerRadius = 5
        
        var line = LineView(frame : CGRect(x : 150, y:10, width:150, height : 70))
        line.backgroundColor = UIColor.clearColor()
        
        addSubview(line)
        
        pv = PositionStatusView(frame : CGRect(x : 10, y:100, width:frame.size.width, height : 30))
        pv.backgroundColor = UIColor.clearColor()
        
        addSubview(pv)
    
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
