//
//  SignUpView.swift
//  iosApp
//
//  Created by Cess UP on 28/09/25.
//

import SwiftUI

struct SignUpView: View {
	@State private var email = ""
	
	var body: some View {
		VStack(spacing: 20) {
			Text("Sign Up")
				.font(.title2)
				.fontWeight(.semibold)
			
			TextField("Enter your email", text: $email)
				.textFieldStyle(.roundedBorder)
				.keyboardType(.emailAddress)
			
			Button("Continue") {
				// Sign up action
			}
			.buttonStyle(.borderedProminent)
			
			Spacer()
		}
		.padding()
		.navigationTitle("Sign Up")
	}
}

#Preview {
	SignUpView()
}
