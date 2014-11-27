//
//  MovieCell.swift
//  rottenex
//
//  Created by Praful Mantale on 11/26/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class MovieCell: UITableViewCell {

    @IBOutlet weak var topLabel: UILabel!
    
    @IBOutlet weak var bottomLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
