//
//  CustCellView.swift
//  ymlistview
//
//  Created by Praful Mantale on 12/2/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class CustCellView: UITableViewCell {

    @IBOutlet weak var rcView: UIView!
    
    @IBOutlet weak var rateView: UIView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
        
                rcView.layer.cornerRadius = 5;
                rcView.clipsToBounds = true
        
                rateView.layer.cornerRadius = 5;
                rateView.clipsToBounds = true;
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
