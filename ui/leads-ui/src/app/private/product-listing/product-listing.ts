import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface Product {
    id: number;
    title: string;
    price: number;
    category: string;
    description: string;
    image: string;
}

@Component({
    selector: 'app-product-listing',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './product-listing.html',
    styleUrl: './product-listing.css'
})
export class ProductListing {
    products: Product[] = [
        {
            id: 1,
            title: 'Premium Wireless Headphones',
            price: 299.99,
            category: 'Electronics',
            description: 'Experience crystal clear sound with our premium noise-cancelling headphones.',
            image: 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=500&auto=format&fit=crop&q=60'
        },
        {
            id: 2,
            title: 'Ergonomic Office Chair',
            price: 199.50,
            category: 'Furniture',
            description: 'Work in comfort with this fully adjustable ergonomic office chair.',
            image: 'https://images.unsplash.com/photo-1592078615290-033ee584e267?w=500&auto=format&fit=crop&q=60'
        },
        {
            id: 3,
            title: 'Smart Fitness Watch',
            price: 149.00,
            category: 'Wearables',
            description: 'Track your health and fitness goals with this advanced smart watch.',
            image: 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=500&auto=format&fit=crop&q=60'
        },
        {
            id: 4,
            title: 'Professional Camera Lens',
            price: 899.00,
            category: 'Photography',
            description: 'Capture stunning photos with this high-quality professional camera lens.',
            image: 'https://images.unsplash.com/photo-1617005082133-548c4dd27f35?w=500&auto=format&fit=crop&q=60'
        },
        {
            id: 5,
            title: 'Minimalist Backpack',
            price: 79.99,
            category: 'Accessories',
            description: 'Stylish and functional backpack for your daily commute or travel.',
            image: 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=500&auto=format&fit=crop&q=60'
        },
        {
            id: 6,
            title: 'Mechanical Keyboard',
            price: 129.99,
            category: 'Electronics',
            description: 'Tactile and responsive mechanical keyboard for typing and gaming.',
            image: 'https://images.unsplash.com/photo-1587829741301-dc798b91a603?w=500&auto=format&fit=crop&q=60'
        }
    ];
}
